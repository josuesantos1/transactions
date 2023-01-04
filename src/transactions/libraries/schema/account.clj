(ns transactions.libraries.schema.account)

(def schema
  [{:db/ident :account/id
    :db/valueType :db.type/uuid
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :account/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :account/email
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :account/password
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :account/document
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :account/status
    :db/valueType :db.type/keyword
    :db/cardinality :db.cardinality/one}
   {:db/ident :account/balance
    :db/valueType :db.type/bigint
    :db/cardinality :db.cardinality/one}])



