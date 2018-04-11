(ns expo-todo.db
  (:require [clojure.spec.alpha :as s]))

;; spec of app-db
(s/def ::greeting string?)

(s/def ::name string?)
(s/def ::key some?)

(s/def ::todo (s/keys :req-un [::name ::key]))
(s/def ::todos (s/every ::todo))

(s/def ::app-db
  (s/keys :req-un [::greeting
                   ::todos]))

;; initial state of app-db
(def app-db {:todos [{:name "Go Shopping" :key "one"}
                     {:name "Make Awesome App" :key "two"}
                     {:name "Add Amazing sounds" :key "three"}
                     {:name "Fill in Timesheets" :key "four"}]
             :greeting "Hello Clojurescript in Expo!"})

(s/explain-str ::app-db app-db)
