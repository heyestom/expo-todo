(ns expo-todo.views.home
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [cljs-react-navigation.re-frame :refer [stack-navigator tab-navigator stack-screen tab-screen router] :as nav]
            [expo-todo.handlers]
            [expo-todo.subs]))

;; TODO move these require into utility name-space
(def ReactNative (js/require "react-native"))

(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def Button (r/adapt-react-class (.-Button ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def flat-list (r/adapt-react-class (.-FlatList ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def Alert (.-Alert ReactNative))

(defn todo-list-item [navigate item]
  (let [name (:name item)]i
    ^{:key item} ;; unique key 
    [touchable-highlight {:onPress #(navigate "TodoDetail" {:todo-name name})
                          :style {:flex-direction "row"
                                  :flex 1
                                  :margin 1
                                  :padding 5
                                  :background-color "#FFF"}}
     [text {:style {:color "black"
                    :font-size 25
                    :text-align "left"}} name]]))

(defn home-screen [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack]} navigation
          todos (subscribe [:get-todos])]
      [scroll-view {:style {:flex-direction "column"
                     :flex 1
                     :background-color "#333"
                     :padding-top 20}}
       (doall (map (partial todo-list-item navigate) @todos))])))

(defn todo-detail [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack state]} navigation
          {:keys [params]} state]
      [view {:style {:flex           1
                     :alignItems     "center"
                     :justifyContent "center"}}
       [text {:style {:color "black"
                      :font-size 25
                      :text-align "left"}} (:todo-name params)]
       [Button {:style   {:fontSize 17}
                :onPress #(goBack)
                :title   "Go Back!"}]])))

;; TODO move routing logic into own name-space 
(def HomeStack
  (stack-navigator
   {:HomeScreen {:screen (stack-screen home-screen {:title "Your To-Dos"})}
    :TodoDetail {:screen (stack-screen todo-detail {:title "Placeholder"})}}))

(defn app-root []
  (fn []
    [:> HomeStack {}]))
