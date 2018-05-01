(ns expo-todo.views.todo-detail
  (:require [cljs-time.format :as time-format]
            [expo-todo.views.react-nateive-components :as rn]
            [re-frame.core :refer [dispatch]]))

(defn todo-detail-navigation-options [{:keys [navigation] :as props}]
  (let [{:keys [navigate goBack state]} navigation
        {:keys [params]} state]
    {:headerTitle "ToDo Details"}))

(defn todo-name-section [name]
  [rn/view {:style {:flex 1}}
   [rn/text {:style {:color "black"
                     :margin 10
                     :font-size 25
                     :text-align "left"}} name]])

(defn date-added-section [created-date]
  [rn/view {:style {:flex 1}}
   [rn/text {:style {:color "black"
                     :margin 10
                     :font-size 25
                     :text-align "left"}}
    (str "Added: "
         (time-format/unparse (time-format/formatter "EEE, dd MMM yyyy")
                              created-date))]])

(defn priority-num->string [priority]
  (get {0 "SUPER URGENT"
        1 "Very Important"
        2 "High Priority"
        3 "Pressing"
        4 "Needs To Happen"
        5 "Should Happen"
        6 "Could be done"
        7 "Would Be Nice To Get Done"
        8 "Ideally Can Be Done"
        9 "Neat Idea"
        10 "Nothing Else To Do!"} 2))

(defn priority-section [priority]
  [rn/view {:style {:flex 1}}
   [rn/text {:style {:color "black"
                     :margin 10
                     :font-size 25
                     :text-align "left"}}
    (str "Priority: " (priority-num->string priority))]])

(defn bottom-button [goBack dispatch-fn colour]
  [rn/view {:flex 1
            :justifyContent "center"
            :style {:padding 5}}
   [rn/touchable-highlight {:onPress #(do (dispatch-fn)
                                          (goBack))
                            :title   "Go Back!"
                            :style   {:background-color colour}}
    [rn/ion-icon {:name "md-add-circle"
                  :size 25
                  :style {:text-align "center"
                          :margin 10}}]]])

(def success-green "#090")
(def dismiss-red "#d00")

(defn todo-detail-screen [props]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack state]} navigation
          {:keys [params]} state
          {:keys [todo-name created-date priority]} params]
      [rn/view {:style {:flex           1
                        :alignItems     "center"
                        :justifyContent "space-between"}}
       [todo-name-section todo-name]
       [date-added-section created-date]
       [priority-section priority]
       ;; white space ....
       [rn/view {:style {:flex 3}}]

       [rn/view {:style {:flex-direction "row"
                         :flex 1}}
        [bottom-button goBack #(dispatch [:delete-todo params]) dismiss-red]
        [bottom-button goBack #(prn "GREEN") success-green]]])))

(def screen-name :TodoDetail)
