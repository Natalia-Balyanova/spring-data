angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

        if ($localStorage.springDataUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springDataUser.token;
        }

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

     $scope.registration = function() {
        $http.post('/registration', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                   $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                   $localStorage.springDataUser = {username: $scope.user.username, token: response.data.token};

                   $scope.user.username = null;
                   $scope.user.password = null;
                   }
                }, function errorCallback(response) {
                     console.log(response.data);
                     alert(response.data.message);
                });
     };

     $scope.tryToAuth = function () {
             $http.post('/auth', $scope.user)
                 .then(function successCallback(response) {
                     if (response.data.token) {
                         $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                         $localStorage.springDataUser = {username: $scope.user.username, token: response.data.token};

                         $scope.user.username = null;
                         $scope.user.password = null;
                     }
                 }, function errorCallback(response) {
                    console.log(response.data);
                    alert("incorrect login/password");
                 });
         };

         $scope.tryToLogout = function () {
             $scope.clearUser();
             if ($scope.user.username) {
                 $scope.user.username = null;
             }
             if ($scope.user.password) {
                 $scope.user.password = null;
             }
         };

         $scope.clearUser = function () {
             delete $localStorage.springDataUser;
             $http.defaults.headers.common.Authorization = '';
         };

         $rootScope.isUserLoggedIn = function () {
             if ($localStorage.springDataUser) {
                 return true;
             } else {
                 return false;
             }
         };

         $scope.showCurrentUserInfo = function () {
             $http.get('/profile')
                 .then(function successCallback(response) {
                     alert('MY NAME IS: ' + response.data.username);
                 }, function errorCallback(response) {
                     alert('UNAUTHORIZED');
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