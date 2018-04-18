(ns expo-todo.views.todo-detail
  (:require [expo-todo.views.react-nateive-components :as rn]))

(defn todo-detail-navigation-options [{:keys [navigation] :as props}]
  (let [{:keys [navigate goBack state]} navigation
        {:keys [params]} state]
    {:headerTitle (:todo-name params)}))

(defn todo-detail-screen [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack state]} navigation
          {:keys [params]} state]
      [rn/view {:style {:flex         1
                        :alignItems     "center"
                        :justifyContent "center"}}
       [rn/text {:style {:color "black"
                         :font-size 25
                         :text-align "left"}} (:todo-name params)]
       [rn/button {:style   {:fontSize 17}
                   :onPress #(goBack)
                   :title   "Go Back!"}]])))

(def screen-name :TodoDetail)
