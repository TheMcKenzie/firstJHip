'use strict';

angular.module('entidadesApp').controller('AddressDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Address', 'Person',
        function($scope, $stateParams, $uibModalInstance, entity, Address, Person) {

        $scope.address = entity;
        $scope.persons = Person.query();
        $scope.load = function(id) {
            Address.get({id : id}, function(result) {
                $scope.address = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('entidadesApp:addressUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.address.id != null) {
                Address.update($scope.address, onSaveSuccess, onSaveError);
            } else {
                Address.save($scope.address, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
