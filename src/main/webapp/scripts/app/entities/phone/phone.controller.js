'use strict';

angular.module('entidadesApp')
    .controller('PhoneController', function ($scope, $state, Phone) {

        $scope.phones = [];
        $scope.loadAll = function() {
            Phone.query(function(result) {
               $scope.phones = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.phone = {
                number: null,
                id: null
            };
        };
    });
