(ns expo-todo.handlers
  (:require [cljs-time.core :as cljs-time]
            [clojure.spec.alpha :as s]
            [expo-todo.db :as db :refer [app-db]]
            [re-frame.core :refer [->interceptor dispatch reg-event-db]]))

;; -- Interceptors ----------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/develop/docs/Interceptors.md
;;
(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (throw (ex-info (str "Spec check failed: " explain-data) explain-data)))))

(def validate-spec
  (if goog.DEBUG
    (->interceptor
        :id :validate-spec
        :after (fn [context]
                 (let [db (-> context :effects :db)]
                   (check-and-throw ::db/app-db db)
                   context)))
    ->interceptor))

;; -- Handlers --------------------------------------------------------------

(reg-event-db
  :initialize-db
  [validate-spec]
  (fn [_ _]
    app-db))

(reg-event-db
 :add-todo
 [validate-spec]
 (fn [db [_ _]]
   (let [new-todo {:todo-name (:new-todo-name db)
                   :created-date (cljs-time/now)
                   :compleated? false
                   :priority 2}]
     (dispatch [:clear-new-todo-state])
     (assoc db :todo-list (conj (:todo-list db) new-todo)))))

(reg-event-db
 :clear-new-todo-state
 [validate-spec]
 (fn [db [_ value]]
   (assoc db :new-todo-name "")))

(reg-event-db
 :update-todo-name
 [validate-spec]
 (fn [db [_ value]]
   (assoc db :new-todo-name value)))

(reg-event-db
 :set-greeting
 [validate-spec]
 (fn [db [_ value]]
   (assoc db :greeting value)))
