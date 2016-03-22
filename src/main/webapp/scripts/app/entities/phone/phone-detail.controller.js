'use strict';

angular.module('entidadesApp')
    .controller('PhoneDetailController', function ($scope, $rootScope, $stateParams, entity, Phone, Person) {
        $scope.phone = entity;
        $scope.load = function (id) {
            Phone.get({id: id}, function(result) {
                $scope.phone = result;
            });
        };
        var unsubscribe = $rootScope.$on('entidadesApp:phoneUpdate', function(event, result) {
            $scope.phone = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
