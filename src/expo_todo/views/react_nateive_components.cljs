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

(def Expo (js/require "expo"))
(def Font (aget Expo "Font"))
(def Asset (aget Expo "Asset"))

(def Ionicons (js/require "@expo/vector-icons/Ionicons"))
(def ion-icon (r/adapt-react-class (aget Ionicons "default")))

(def FontAwesome (js/require "@expo/vector-icons/FontAwesome"))
(def fontawesome-icon (r/adapt-react-class (aget FontAwesome "default")))

(def alert (.-Alert ReactNative))
