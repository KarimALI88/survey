import React, {createContext, useState} from 'react'

export const AppContext = createContext()

const ContextProvider = (props) => {
    const [language,setLanguage] = useState("en")
    return (
        <AppContext.Provider value={{language, setLanguage}}>
            {props.children}
        </AppContext.Provider>
    )
}


export default ContextProvider