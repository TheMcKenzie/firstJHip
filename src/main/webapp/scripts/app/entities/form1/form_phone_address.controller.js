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
        
        var names = [];
        $scope.saveToAddressArray = function () {
        	console.log("In function");
        	//names.push(document.getElementById("address_na"));//.value + ",";
        	//console.log("The names "+String(names));
        	$scope.address = {
                name: null
            };
        };
        
        $scope.saveToPhonesArray = function () {
        	console.log("In function");
        	//var phones = document.getElementById("phone_num").value;
        	//console.log("The phones "+phones);
        	$scope.phone = {
                number: null
            };
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
        	
            $scope.person = {
                name: null,
                id: null
            };
            $scope.address = {
                name: null
            };
            $scope.phone = {
                number: null
            };
        };
        
        
    });