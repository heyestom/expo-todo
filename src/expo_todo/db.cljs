(ns expo-todo.db
  (:require [cljs-time.core :as cljs-time]
            [clojure.spec.alpha :as s]))

;; spec of app-db
(s/def ::greeting string?)

(s/def ::todo-name string?)
(s/def ::created-date cljs-time/date?)
(s/def ::compleated? boolean?)
(s/def ::priority (s/int-in 1 10))

(s/def ::todo-item (s/keys :req-un [::todo-name
                                    ::created-date
                                    ::compleated?
                                    ::priority]))

(s/def ::todo-list (s/coll-of ::todo-item))

(s/def ::app-db
  (s/keys :req-un [::greeting
                   ::todo-list]))

;; initial state of app-db
(def app-db {:todo-list [{:todo-name "Go Shopping"
                          :created-date (cljs-time/date-time 2018 04 01)
                          :compleated? false
                          :priority 2}
                         {:todo-name "Make Awesome App"
                          :created-date (cljs-time/date-time 2018 03 02)
                          :compleated? false
                          :priority 4}
                         {:todo-name "Add Amazing sounds"
                          :created-date (cljs-time/date-time 2018 04 20)
                          :compleated? false
                          :priority 6}
                         {:todo-name "Fill in Time-sheets"
                          :created-date (cljs-time/date-time 2018 04 05)
                          :compleated? false
                          :priority 1}]
             :greeting "Hello Clojurescript in Expo!"})

(s/explain-str ::app-db app-db)
(s/valid? ::app-db app-db)
