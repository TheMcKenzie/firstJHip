 'use strict';

angular.module('entidadesApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-entidadesApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-entidadesApp-params')});
                }
                return response;
            }
        };
    });
