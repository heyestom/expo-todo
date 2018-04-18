(ns expo-todo.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [expo-todo.nav-root :as nav-root]
              [expo-todo.handlers]
              [expo-todo.subs]))

(def Expo (js/require "expo"))
(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))

;; react-navigation
(def ReactNavigation (js/require "react-navigation"))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerRootComponent Expo (r/reactify-component nav-root/app-root)))
