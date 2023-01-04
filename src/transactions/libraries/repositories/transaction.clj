(ns transactions.libraries.repositories.transaction)

(defn create-id
  ([n]
   (create-id n [(rand-int 9)]))
  ([n id]
   (if (= n 1)
     (apply str id)
     (->> (apply merge id [(rand-int 9)])
          (create-id (- n 1))))))

(defn new-transaction 
  [{:keys [sender receiver value]}]
  (println sender receiver value)
  (let [id (create-id 20)]
    [[:db/add id :transaction/id (java.util.UUID/randomUUID)]
     [:db/add id :transaction/sender sender]
     [:db/add id :transaction/receiver receiver]
     [:db/add id :transaction/value value]
     [:db/add id :transaction/status :status/sent]]))

#_(new-transaction {:uuid "12345" :sender "123456" :receiver "234513" :value 100000N})


