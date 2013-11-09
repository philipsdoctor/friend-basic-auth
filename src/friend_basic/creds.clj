(ns friend-basic.creds
  (:require [crypto.password.scrypt :as password]))



(def users (atom {"friend" {:username "friend"
                            :encrypted-password (password/encrypt "clojure")
                            }
                  "friend-admin" {:username "friend-admin"
                                  :encrypted-password (password/encrypt "clojure")
                                  }
                  }))

(defn check-password
  "Returns nil or {:username blah :sid blah}"
  [username password]
  (let [m (get @users username)]
    (println (str m))
    (cond
      (nil? m)
        nil
      (password/check password (:encrypted-password m))
        (dissoc m :encrypted-password)
      :default nil)))

(defn scrypt-friend-cred-fn
  "Credential function for Friend"
  [{:keys [username password]}]
  (check-password username password))

