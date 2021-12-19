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
                $scope.ProductsPage = response.data;
                console.log($scope.ProductsList);
            });
     };
        //первая страница по дефолту/сброс фильтра
     $scope.loadProductsDefault = function (pageIndex = 1) {
            $http.get(contextPath + '/products')
                .then(function (response) {
                    $scope.ProductsPage = response.data;
                });
     }

        $scope.addToCart = function (productId){
            $http.get(contextPath + '/products/cart/' + productId)
                        .then(function (response) {
                              $scope.CartPage = response.data;
                              $scope.loadCartPage();
                        });
        }

         $scope.loadCartPage = function (){
             $http.get(contextPath + '/products/cart/cartInfo/')
                        .then(function (response) {
                            $scope.CartPage = response.data;
                        });
         }

         $scope.deleteProductFromCart = function (productId) {
                $http.delete(contextPath + '/products/cart/cartInfo/' + productId)
                         .then(function (response) {
                             console.log(response.data)
                             $scope.loadCartPage();
                         });
                }

        $scope.deleteProduct = function (productId) {
            $http.delete(contextPath + '/products/' + productId)
                .then(function (response) {
                    console.log(response.data)
                    $scope.loadProducts();
                });
     }

        $scope.createProductJson = function () {
            console.log($scope.newProductJson);
            $http.post(contextPath + '/products/', $scope.newProductJson)
                           .then(function (response) {
                                $scope.loadProducts();
                           });
     }

        $scope.updateProduct = function () {
            $http.put(contextPath + '/products', $scope.updated_product)
                            .then(function (response) {
                                console.log($scope.updated_product);
                                $scope.loadProducts();
                                $scope.updated_product = null;
                             });
     }

        $scope.prepareProductForUpdate = function (productId) {
            $http.get(contextPath + '/products/' + productId)
                        .then(function (response) {
                                $scope.updated_product = response.data;
                             });
     }
//неработающий метод
//         $scope.changeAmount = function (productId, amount) {
//                $http({
//                         url: contextPath + '/products/cart/change_amount',
//                         method: 'get',
//                         params: {
//                             productId: productId,
//                             amount: amount
//                         }
//                     }).then(function (response) {
//                         console.log(response.data)
//                         $scope.loadCartPage();
//                     });
//                 }
//         $scope.loadCartPage();

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

        $scope.loadProducts();
});