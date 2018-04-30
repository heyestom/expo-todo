(ns expo-todo.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :get-greeting
 (fn [db _]
   (:greeting db)))

(reg-sub
 :get-todo-list
 (fn [db _]
   (:todo-list db)))
