'use strict';

angular.module('entidadesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('phone', {
                parent: 'entity',
                url: '/phones',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Phones'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/phone/phones.html',
                        controller: 'PhoneController'
                    }
                },
                resolve: {
                }
            })
            .state('phone.detail', {
                parent: 'entity',
                url: '/phone/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Phone'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/phone/phone-detail.html',
                        controller: 'PhoneDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Phone', function($stateParams, Phone) {
                        return Phone.get({id : $stateParams.id});
                    }]
                }
            })
            .state('phone.new', {
                parent: 'phone',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/phone/phone-dialog.html',
                        controller: 'PhoneDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    number: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('phone', null, { reload: true });
                    }, function() {
                        $state.go('phone');
                    })
                }]
            })
            .state('phone.edit', {
                parent: 'phone',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/phone/phone-dialog.html',
                        controller: 'PhoneDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Phone', function(Phone) {
                                return Phone.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('phone', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('phone.delete', {
                parent: 'phone',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/phone/phone-delete-dialog.html',
                        controller: 'PhoneDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Phone', function(Phone) {
                                return Phone.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('phone', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
