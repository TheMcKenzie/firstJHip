'use strict';

angular.module('entidadesApp').controller('PhoneDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Phone', 'Person',
        function($scope, $stateParams, $uibModalInstance, entity, Phone, Person) {

        $scope.phone = entity;
        $scope.persons = Person.query();
        $scope.load = function(id) {
            Phone.get({id : id}, function(result) {
                $scope.phone = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('entidadesApp:phoneUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.phone.id != null) {
                Phone.update($scope.phone, onSaveSuccess, onSaveError);
            } else {
                Phone.save($scope.phone, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
