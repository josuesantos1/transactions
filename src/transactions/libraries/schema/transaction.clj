(ns transactions.libraries.schema.transaction)


(def schema
  [{:db/ident :transaction/id
    :db/valueType :db.type/uuid
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :transaction/sender
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :transaction/receiver
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :transaction/value
    :db/valueType :db.type/bigint
    :db/cardinality :db.cardinality/one}
   {:db/ident :transaction/status
    :db/valueType :db.type/keyword
    :db/cardinality :db.cardinality/one}
   {:db/ident :transaction/instant
    :db/valueType :db.type/instant
    :db/cardinality :db.cardinality/one}])


