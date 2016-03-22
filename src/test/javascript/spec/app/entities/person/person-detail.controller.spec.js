'use strict';

describe('Controller Tests', function() {

    describe('Person Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPerson, MockAddress;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPerson = jasmine.createSpy('MockPerson');
            MockAddress = jasmine.createSpy('MockAddress');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Person': MockPerson,
                'Address': MockAddress
            };
            createController = function() {
                $injector.get('$controller')("PersonDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'entidadesApp:personUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
