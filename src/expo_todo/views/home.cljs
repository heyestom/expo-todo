(ns expo-todo.views.home
  (:require [expo-todo.views.new-todo :as new-todo]
            [expo-todo.views.react-nateive-components :as rn]
            [re-frame.core :refer [subscribe]]))

(defn todo-list-item [navigate item]
  (let [todo-name (:todo-name item)]
    ^{:key item} ;; unique key
    [rn/touchable-highlight {:onPress #(navigate "TodoDetail" item)
                             :style {:flex-direction "row"
                                     :flex 1
                                     :margin 1
                                     :padding 5
                                     :background-color "#FFF"}}
     [rn/text {:style {:color "black"
                       :font-size 14
                       :text-align "left"}} todo-name]]))

(defn home-screen [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack]} navigation
          todo-list (subscribe [:get-todo-list])]
      [rn/scroll-view {:style {:flex-direction "column"
                               :flex 1
                               :background-color "#333"
                               :padding-top 20}}
       (doall (map (partial todo-list-item navigate) @todo-list))])))

(defn home-navigation-options [{:keys [navigation] :as props}]
  (let [{:keys [navigate goBack state]} navigation
        {:keys [params]} state]
    {:headerTitle "Your ToDos"
     :headerRight (fn []
                    [rn/touchable-highlight
                     {:onPress #(navigate (name new-todo/screen-name))}
                     [rn/ion-icon {:name "md-add-circle"
                                   :size 25
                                   :style {:margin 10}}]])}))

(def screen-name :HomeScreen)
