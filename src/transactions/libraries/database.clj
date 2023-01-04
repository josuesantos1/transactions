(ns transactions.libraries.database
  (:require [datomic.api :as d]))

#_(use 'clojure.pprint)

(def db-uri "datomic:mem://transactions")
;; (def db-uri "datomic:dev://localhost:4334/transactions")

(defn create-db [] (d/create-database db-uri))
(defn delete-db [] (d/delete-database db-uri))

#_(delete-db)
#_(create-db)

(def conn (d/connect db-uri))

(defn create-schema [schema] (d/transact conn  schema)) 
