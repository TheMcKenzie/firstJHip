'use strict';

angular.module('entidadesApp')
    .controller('AddressDetailController', function ($scope, $rootScope, $stateParams, entity, Address, Person) {
        $scope.address = entity;
        $scope.load = function (id) {
            Address.get({id: id}, function(result) {
                $scope.address = result;
            });
        };
        var unsubscribe = $rootScope.$on('entidadesApp:addressUpdate', function(event, result) {
            $scope.address = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
