'use strict';

angular.module('entidadesApp')
    .controller('FormPhoneController', function ($scope, $state, Person, Address, Phone) {

        $scope.persons = [];
        $scope.loadAll = function() {
            Person.query(function(result) {
               $scope.persons = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.person = {
                name: null,
                id: null
            };
        };
    });