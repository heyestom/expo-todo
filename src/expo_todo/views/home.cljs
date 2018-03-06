(ns expo-todo.views.home
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [expo-todo.handlers]
              [expo-todo.subs]))

(def ReactNative (js/require "react-native"))

(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def Alert (.-Alert ReactNative))

(defn alert [title]
  (let [greeting (subscribe [:get-greeting])]
    (.alert Alert (str title " " @greeting))))

(defn set-greeting [greeting]
  (dispatch [:set-greeting  greeting]))

(defn set-greeting-button [greeting]
  [touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                        :on-press #(set-greeting greeting)}
   [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]])

(defn home-screen []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
       [image {:source (js/require "./assets/images/cljs.png")
               :style {:width 200
                       :height 200}}]
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
       [touchable-highlight {:style {:background-color "#999"
                                     :padding 10
                                     :border-radius 5}
                             :on-press #(alert "HELLO JOE!")}
        [text {:style {:color "white"
                       :text-align "center"
                       :font-weight "bold"}}
         "press me"]]
       [set-greeting-button "GOOD DAY SIR!"]
       [set-greeting-button "GOOD DAY MADAM!"]
       ])))
