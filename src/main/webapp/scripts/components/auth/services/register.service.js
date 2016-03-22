'use strict';

angular.module('entidadesApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


