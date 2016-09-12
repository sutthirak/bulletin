var bulletinApp = angular.module('bulletinApp', [
        'ngRoute',
        ]);

bulletinApp.config(['$routeProvider',
    function ($routeProvider) {

        $routeProvider.
            when('/index', {
                templateUrl: 'template/index.html',
                controller: 'bulletinCtrl'
            }).when('/view/:topicId', {
                templateUrl: 'template/view.html',
                controller: 'bulletinCtrl'
            }).when('/new', {
                templateUrl: 'template/new.html',
                controller: 'bulletinCtrl'
            }).when('/edit/:topicId', {
                templateUrl: 'template/edit.html',
                controller: 'bulletinCtrl'
            }).when('/delete/:topicId', {
                templateUrl: 'template/delete.html',
                controller: 'bulletinCtrl'
            }).otherwise({
                redirectTo: '/index'
        });

}]).run(function($rootScope,
                   $http,
                   $location,
                   $window,
                   $route,
                   $routeParams,
                   $sce) {

        $rootScope.$on("$viewContentLoaded", function() {

               if($route.current.templateUrl === "template/index.html"){

                    $rootScope.currentPage = 0;
                    if($location.search().p != undefined & parseInt($location.search().p) >1){
                        page = parseInt($location.search().p) - 1;
                        $rootScope.currentPage = page;
                    }
                    $rootScope.redirectPage = $rootScope.currentPage +1;

                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/topics?p='+$rootScope.currentPage,
                    }).then(function successCallback(response) {
                        $rootScope.topics = response.data;

                        console.log($rootScope.topics)
                        console.log($rootScope.topics.length);

                        for(var i = 0;i< $rootScope.topics.content.length;i++){
                            var date = new Date($rootScope.topics.content[i].createdDate);
                            var day = date.getDate();
                            var monthIndex = date.getMonth() + 1;
                            var year = date.getFullYear();
                            $rootScope.topics.content[i].createdDate = day+'/'+monthIndex +'/'+year;
                        }

                    }, function errorCallback(response) {
                        console.error('error');
                        console.error(response);
                    });

               }

               if($route.current.templateUrl === "template/view.html"){

                   $http({
                       method: 'GET',
                       url: 'http://localhost:8080/topics/'+$routeParams.topicId,
                   }).then(function successCallback(response) {
                       $rootScope.topic = response.data;

                       $http({
                           method: 'GET',
                           url: 'http://localhost:8080/topics/'+$routeParams.topicId+'/comments',
                       }).then(function successCallback(response) {
                           $rootScope.comments = response.data;

                       }, function errorCallback(response) {
                           console.error('error');
                           console.error(response);
                       });

                   }, function errorCallback(response) {
                       console.error('error');
                       console.error(response);
                   });

              }

               if($route.current.templateUrl === "template/edit.html"){

                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/topics/'+$routeParams.topicId,
                    }).then(function successCallback(response) {
                        $rootScope.topic = response.data;
                        $rootScope.topic.writerPassword = '';

                    }, function errorCallback(response) {
                        console.error('error');
                        console.error(response);
                    });
               }

        });

});

bulletinApp.controller('bulletinCtrl', function ($rootScope,
                                        $scope,
                                        $http,
                                        $location,
                                        $routeParams,
                                        $window,
                                        $sce){

    $scope.init = function(){

    }

    $scope.newTopic = function(){

        var request = {
            "title": $scope.title,
            "description": $scope.detail,
            "writerTitle":$scope.writerTitle,
            "writerEmail": $scope.writerEmail,
            "writerName": $scope.writerName,
            "writerPassword": $scope.writerPassword
        }

        $http({
            method: 'POST',
            url: 'http://localhost:8080/topics',
            data: request
        }).then(function successCallback(response) {

            $location.path("/index");

        }, function errorCallback(response) {
            console.error('error');
            console.error(response);
            $scope.isError = true;
        });

    }

    $scope.deleteTopic = function(){

        $scope.isError = false;

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/topics/'+$routeParams.topicId,
            data: $scope.deletePassword
        }).then(function successCallback(response) {

            if(response.status == 200){
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/topics',
                }).then(function successCallback(response) {
                    $rootScope.topics = response.data;
                    $location.path("/index");

                }, function errorCallback(response) {
                    console.error('error');
                    console.error(response);
                });

            }else{
                $scope.isError = true;
            }

        }, function errorCallback(response) {
            console.error('error');
            console.error(response);

            $scope.isError = true;
        });
    }


    $scope.updateTopic = function(){

         $scope.isError = false;
         var request = {
             "id" : $routeParams.topicId,
             "title": $scope.topic.title,
             "description": $scope.topic.description,
             "writerName": $scope.topic.writerName,
             "writerPassword": $scope.topic.writerPassword
         }


         $http({
             method: 'PUT',
             url: 'http://localhost:8080/topics/'+$routeParams.topicId,
             data: request
         }).then(function successCallback(response) {

             console.log(response.status);

             if(response.status == 200){
                $location.path("/index");
             }else{
                console.log('found error');
                $scope.isError = true;
             }
         }, function errorCallback(response) {
             console.error('error');
             console.error(response);

             $scope.isError = true;
         });
    }

    $scope.submitComment = function(){

        var request = {
            "topicId": $routeParams.topicId,
            "name": $scope.commentName,
            "detail":$scope.commentDetail
        }

        $http({
            method: 'POST',
            url: 'http://localhost:8080/topics/'+$routeParams.topicId+'/comments',
            data: request
        }).then(function successCallback(response) {

            $http({
                method: 'GET',
                url: 'http://localhost:8080/topics/'+$routeParams.topicId+'/comments',
            }).then(function successCallback(response) {
                $rootScope.comments = response.data;
                $scope.commentName = '';
                $scope.commentDetail = '';

            }, function errorCallback(response) {
                console.error('error');
                console.error(response);
            });

        }, function errorCallback(response) {
            console.error('error');
            console.error(response);
        });

    }

    $scope.redirect = function(){
        var r = '/index?p='+$scope.redirectPage;
        $location.path('/index').search('p',$scope.redirectPage);
    }

});
