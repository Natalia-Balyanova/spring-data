angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http ({
            url: contextPath + '/products',
            method: 'get',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part: null,
                min_price: $scope.filter ? $scope.filter.min_price: null,
                max_price: $scope.filter ? $scope.filter.max_price: null
                }
            }).then(function (response) {
                $scope.ProductsList = response.data.content;
                console.log($scope.ProductsList);
            });
     };
        //первая страница по дефолту/сброс фильтра
     $scope.loadProductsDefault = function (pageIndex = 1) {
            $http.get(contextPath + '/products')
                .then(function (response) {
                    $scope.ProductsList = response.data.content;
                });
        }

        $scope.deleteProduct = function (productId) {
            $http.delete(contextPath + '/products/' + productId)
                .then(function (response) {
                    console.log(response.data)
                    $scope.loadProducts();
                });
        }
    //старое дз
//        $scope.changePrice = function (productId, delta) {
//            $http({
//                url: contextPath + 'products/change_price',
//                method: 'get',
//                params: {
//                    productId: productId,
//                    delta: delta
//                }
//            }).then(function (response) {
//                console.log(response.data)
//                $scope.loadProducts();
//            });
//        }

        $scope.createProductJson = function () {
            console.log($scope.newProductJson);
            $http.post(contextPath + '/products', $scope.newProductJson)
                           .then(function (response) {
                                $scope.loadProducts();
                           });
        }

        //старое дз
//        $scope.filterProducts = function() {
//            console.log($scope.findProductBetween);
//            $http({
//                   url: contextPath + '/price_between',
//                   method: 'get',
//                   params: {
//                       min: $scope.findProductBetween.min,
//                       max: $scope.findProductBetween.max
//                   }
//                }).then(function (response) {
//                        console.log(response.data);
//                        $scope.ProductsList = response.data;
//                        $scope.findProductBetween.min = null;
//                        $scope.findProductBetween.max = null;
//                });
//        }

        $scope.loadProducts();
});