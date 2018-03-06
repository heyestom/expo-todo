(ns ^:figwheel-no-load env.expo.main
  (:require [reagent.core :as r]
            [expo-todo.core :as core]
            [expo-todo.views.home :as home]
            [figwheel.client :as figwheel :include-macros true]
            [env.dev]))

(enable-console-print!)

(def cnt (r/atom 0))
(defn reloader [] @cnt [home/home-screen])
(def root-el (r/as-element [reloader]))

(figwheel/watch-and-reload
 :websocket-url (str "ws://" env.dev/ip ":3449/figwheel-ws")
 :heads-up-display false
 :jsload-callback #(swap! cnt inc))

(core/init)
