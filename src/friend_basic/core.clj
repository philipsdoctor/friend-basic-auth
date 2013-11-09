(ns friend-basic.core
  (:require
            [hiccup.middleware :refer [wrap-base-url]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.util.response :as resp]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site api]]
            [cemerick.friend :as friend]
            [friend-basic.creds :as creds]
            (cemerick.friend [workflows :as workflows])
            ring.adapter.jetty
            [compojure.core :refer (GET POST defroutes)]))

(defn generate-response
  [code msg]
  {:status code
   :body msg
   :headers {"Content-Type" "text/plain"}})

(defroutes app*
  (POST "/v0/r" [] (generate-response 200 "Authed")))

(def secured-app (friend/authenticate
                   app*
                   {:allow-anon? false
                    :unauthenticated-handler #(workflows/http-basic-deny "Friend basic test " %)
                    :workflows [(workflows/http-basic
                                 :credential-fn creds/scrypt-friend-cred-fn
                                 :realm "Friend basic")]}))

(def app (api secured-app))

(defn run
  []
    (ring.adapter.jetty/run-jetty #'app {:port 3000 :join? false}))

