(ns expo-todo.views.new-todo
  (:require [re-frame.core :refer [dispatch subscribe]]
            [expo-todo.views.react-nateive-components :as rn]))

(def naviagtion-options
  {:title "Add ToDo"})

(defn new-todo-screen [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [goBack]} navigation]
      [rn/view {:style {:flex-direction "column"
                        :flex 1
                        :background-color "#333"}}
       [rn/text-input {:style {:height 40
                               :background-color "#ddd"}
                       :placeholder "New ToDo Name"
                       :onChangeText (fn [text] (dispatch [:update-todo-name text]))
                       }]
       [rn/button {:style {:fontSize 16}
                   :title "Save Todo!"
                   :onPress (fn []
                              (dispatch [:add-todo])
                              (goBack))}]
       ])))

(def screen-name :NewTodo)
