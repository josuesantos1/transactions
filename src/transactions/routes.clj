(ns transactions.routes 
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]))

(def common-interceptors [(body-params/body-params) http/json-body])

(defn about-page
  [request]
  {:status 200 :body  (format "Clojure %s"
                              (clojure-version))})

(def routes #{["/about" :get (conj common-interceptors `about-page)]})