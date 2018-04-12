(ns expo-todo.views.react-nateive-components
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))

(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))

(def text-input (r/adapt-react-class (.-TextInput ReactNative)))

(def flat-list (r/adapt-react-class (.-FlatList ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))

;; currently causing issues with unrecognised font family 
(defonce Ionicons (.-Ionicons (js/require "@expo/vector-icons")))
(defonce Entypo (.-Entypo (js/require "@expo/vector-icons")))

(def alert (.-Alert ReactNative))
