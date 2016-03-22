'use strict';

angular.module('entidadesApp').controller('PersonDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Person', 'Address',
        function($scope, $stateParams, $uibModalInstance, entity, Person, Address) {

        $scope.person = entity;
        $scope.addresss = Address.query();
        $scope.load = function(id) {
            Person.get({id : id}, function(result) {
                $scope.person = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('entidadesApp:personUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.person.id != null) {
                Person.update($scope.person, onSaveSuccess, onSaveError);
            } else {
                Person.save($scope.person, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
