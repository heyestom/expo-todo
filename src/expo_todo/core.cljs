(ns expo-todo.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [expo-todo.views.home :as home]
              [expo-todo.handlers]
              [expo-todo.subs]))

(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))

;; react-navigation
(def ReactNavigation (js/require "react-navigation"))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent app-registry "main" #(r/reactify-component home/app-root)))


;;(defn init []
;;  (dispatch-sync [:initialize-db])
;;  (.registerComponent app-registry "main" #(r/reactify-component home/home-screen)))
