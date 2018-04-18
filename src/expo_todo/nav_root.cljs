(ns expo-todo.nav-root
  (:require [cljs-react-navigation.re-frame :as nav]
            [expo-todo.views.home :as home]
            [expo-todo.views.new-todo :as new-todo]
            [expo-todo.views.todo-detail :as todo-detail]
            [reagent.core :as r]))

(def HomeStack
  (r/adapt-react-class
   (nav/stack-navigator
    {home/screen-name        {:screen (nav/stack-screen home/home-screen
                                                        home/home-navigation-options)}
     new-todo/screen-name    {:screen (nav/stack-screen new-todo/new-todo-screen
                                                        new-todo/naviagtion-options)}
     todo-detail/screen-name {:screen (nav/stack-screen todo-detail/todo-detail-screen
                                                        todo-detail/todo-detail-navigation-options)}})))

(defn app-root []
  (r/create-class
   {:reagent-render (fn [] [HomeStack {}])}))

