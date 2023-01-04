(ns transactions.service
  (:require [io.pedestal.http :as http]
            [transactions.routes :as routes]
            
            [transactions.libraries.database :as db]
            [transactions.libraries.schema.transaction :as schema-tran]))

(def service {:env :prod
              ::http/routes routes/routes
              ::http/resource-path "/public"
              ::http/type :jetty
              ::http/host "0.0.0.0"
              ::http/port 8080
              ::http/container-options {:h2c? true
                                        :h2? false
                                        :ssl? false}})

(defn create-schema
  []
  (-> schema-tran/schema
      (db/create-schema)))

(defn startup-app
  []
  (db/create-db)
  (create-schema))

(startup-app)
