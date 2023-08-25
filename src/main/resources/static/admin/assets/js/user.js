new Vue({
    el: "#app",

    data() {



        return {
            User: {
                userId: '',
                userEmail: '',
                userName: '',
                userPass: '',
                userTele: '',
                userRegDate: ''
            }
        }
    },
    mounted() {
        passwordLogin();

    },
    methods: {


        async passwordLogin() {


            const getUserRes = await getUser(this.User);
            if (getUserRes.data.code === 1) {
                //验证通过
                console.log(this.getUserRes);

            } else {
                console.log("wrong");
            }




        },



        resetForm(formName) {
            this.inputStatus = false;
            this.$refs[formName].resetFields();
        },
    }
})