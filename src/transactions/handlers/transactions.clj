(ns transactions.handlers.transactions
  (:require [transactions.libraries.repositories.transaction :as repo.transaction]
            [transactions.libraries.repositories.account :as repo.account]
            [transactions.libraries.database :as db]
            [datomic.api :as d]))

(defn- contain-cache
  [value email conn]
  (let [balance (get (repo.account/get-balance email conn) 0)]
    (if (and (:account/balance balance) (>= (:account/balance balance) value))
      (:account/balance balance)
      nil)))

(defn- create-transaction
  [params conn]
  (->> (repo.transaction/new-transaction params)
       (d/transact conn)))

(defn- update-balance
  [sender receiver value balance]
  (println sender receiver )
  (d/transact db/conn (repo.account/update-balance sender (- balance value)))
  (d/transact db/conn (repo.account/update-balance receiver (+ balance value))))

#_(repo.account/get-balance "josue.santos@email.com" db/conn)

(defn- get-id
  [sender receiver conn]
  {:sender (:account/id (get (repo.account/view-user sender conn) 0))
   :receiver (:account/id (get (repo.account/view-user receiver conn) 0))})

(defn new-transaction
  [{:keys [json-params]}]
  (let [value (:value json-params)
        balance (contain-cache value (:sender json-params) db/conn)
        {:keys [sender receiver value]} json-params
        {:keys [sender receiver]} (get-id sender receiver db/conn)]
    (if balance
      (do (create-transaction json-params db/conn)
          (update-balance sender receiver value balance))
      {:status 400 :body "insufficient money"})))


#_(use 'clojure.pprint)
#_(def tx (new-transaction {:json-params {:uuid "12345" :sender "josue.santos@email.com" :receiver "234513" :value 199N}}))
#_(pprint @tx)
#_tx

(new-transaction {:json-params {:uuid "12345" :sender "josue.santos@email.com" :receiver "josue@email.com" :value 199N}})
#_(pprint (repo.trans/view-all-transaction-by-user "123456" db/conn))
#_(pprint (repo-trans/view-transaction-by-id #uuid"4d829a3d-62b9-4718-a692-9e347b89fe88" db/conn))
#_(pprint (repo-trans/revert-transaction #uuid"4d829a3d-62b9-4718-a692-9e347b89fe88" db/conn))

