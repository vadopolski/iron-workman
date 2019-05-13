if (process.env.NODE_ENV === "production") {
    const opt = require("./iron-workman-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./iron-workman-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./iron-workman-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
