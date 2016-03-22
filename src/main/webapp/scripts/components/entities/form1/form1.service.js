'use strict';

angular.module('entidadesApp')
    .factory('Person', function ($resource, DateUtils) {
        return $resource('api/form/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
