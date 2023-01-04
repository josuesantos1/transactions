(ns transactions.libraries.repositories.account
  (:require [datomic.api :as d]))

#_(require '[transactions.libraries.database :as db])

(defn create-id
  ([n]
   (create-id n [(rand-int 9)]))
  ([n id]
   (if (= n 1)
     (apply str id)
     (->> (apply merge id [(rand-int 9)])
          (create-id (- n 1))))))

(defn new-user
  [{:keys [name email password document]}]
  (let [id (create-id 20)]
    [[:db/add id :account/id (java.util.UUID/randomUUID)]
     [:db/add id :account/name name]
     [:db/add id :account/email email]
     [:db/add id :account/password password]
     [:db/add id :account/document document]
     [:db/add id :account/status :status/to-validate]
     [:db/add id :account/balance 0N]]))


#_(d/transact db/conn (new-user {:name "josue" :email "josue@email.com" :password "123456" :document "123456789"}))

(defn insert-email
  [{:keys [id email]}]
  (let [tx-id (create-id 20)]
    [[:db/add tx-id :account/id id]
     [:db/add tx-id :account/email email]]))

#_(d/transact db/conn (insert-email {:email "josue.santos@email.com" :id #uuid "5727bdd1-1c86-4f87-acd2-3db2873b3e99"}))

(defn validate-user
  [id]
  (let [tx-id (create-id 20)]
    [[:db/add tx-id :account/id id]
     [:db/add tx-id :account/status :status/validated]]))

#_(d/transact db/conn (validate-user #uuid "5727bdd1-1c86-4f87-acd2-3db2873b3e99"))

(defn change-password
  [{:keys [id password]}]
  (let [tx-id (create-id 20)]
    [[:db/add tx-id :account/id id]
     [:db/add tx-id :account/password password]]))

#_(d/transact db/conn (change-password {:id #uuid "5727bdd1-1c86-4f87-acd2-3db2873b3e99" :password "019283"}))

(defn update-balance
  [{:keys [id balance]}]
  (let [tx-id (create-id 20)]
    [[:db/add tx-id :account/id id]
     [:db/add tx-id :account/balance balance]])) 

#_(d/transact db/conn (update-balance {:id #uuid "5727bdd1-1c86-4f87-acd2-3db2873b3e99" :balance 1000N}))

(defn view-user
  [email conn]
  (d/q '[:find (pull ?e [*])
         :in $ ?email
         :where
         [?e :account/email ?email]] (d/db conn) email))

#_(view-user "josue.santos@email.com" db/conn)

#_(d/q '[:find (pull ?e [*])
       :where
       [?e :account/name "josue"]] (d/db db/conn))
