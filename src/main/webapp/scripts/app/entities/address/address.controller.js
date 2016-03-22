'use strict';

angular.module('entidadesApp')
    .controller('AddressController', function ($scope, $state, Address) {

        $scope.addresss = [];
        $scope.loadAll = function() {
            Address.query(function(result) {
               $scope.addresss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.address = {
                name: null,
                id: null
            };
        };
    });
