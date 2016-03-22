'use strict';

angular.module('entidadesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('person', {
                parent: 'entity',
                url: '/form1',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Form 1'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/form1/form_phone_address.html',
                        controller: 'FormPhoneController'
                    }
                },
                resolve: {
                }
            })
    });
