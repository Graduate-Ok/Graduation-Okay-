import { atom } from 'recoil';

let toggleState = atom({
    key: 'toggle',
    default: false,
});

export default toggleState;
