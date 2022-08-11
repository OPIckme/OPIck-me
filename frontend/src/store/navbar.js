export const navbar = {
    state: {
        here1: "on",
        here2: "off",
    },

    getters: {
      here1: state => state.here1,
      here2: state => state.here2,
    },

    mutations: {
        SET_HERE1: (state, here1) => state.here1 = here1,
        SET_HERE2: (state, here2) => state.here2 = here2,
    },

    actions: {
        fetchHere1( { commit } ) {
            commit('SET_HERE1', "on")
            commit('SET_HERE2', "off")
        },

        fetchHere2( { commit } ) {
          commit('SET_HERE1', "off")
          commit('SET_HERE2', "on")
        },
    },
}