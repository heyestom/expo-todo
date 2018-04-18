(ns expo-todo.views.home
  (:require [cljs-react-navigation.re-frame :as nav]
            [expo-todo.views.new-todo :as new-todo]
            [expo-todo.views.react-nateive-components :as rn]
            [expo-todo.views.todo-detail :as todo-detail]
            [re-frame.core :refer [subscribe]]
            [reagent.core :as r]))

(defn todo-list-item [navigate item]
  (let [name (:name item)]
       ^{:key item} ;; unique key
       [rn/touchable-highlight {:onPress #(navigate "TodoDetail" {:todo-name name})
                                :style {:flex-direction "row"
                                        :flex 1
                                        :margin 1
                                        :padding 5
                                        :background-color "#FFF"}}
        [rn/text {:style {:color "black"
                          :font-size 14
                          :text-align "left"}} name]]))

(defn home-screen [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack]} navigation
          todos (subscribe [:get-todos])]
      [rn/scroll-view {:style {:flex-direction "column"
                               :flex 1
                               :background-color "#333"
                               :padding-top 20}}
       (doall (map (partial todo-list-item navigate) @todos))])))

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

;; TODO move routing logic into own name-space 
(def HomeStack
  (nav/stack-navigator
   {:HomeScreen {:screen (nav/stack-screen home-screen home-navigation-options)}
    new-todo/screen-name {:screen (nav/stack-screen new-todo/new-todo-screen new-todo/naviagtion-options)}
    todo-detail/screen-name  {:screen (nav/stack-screen todo-detail/todo-detail-screen todo-detail/todo-detail-navigation-options)}}))

(defn app-root []
  (r/create-class
   {:reagent-render (fn [] [:> HomeStack {}])}))
