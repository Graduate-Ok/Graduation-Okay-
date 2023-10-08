import { useEffect } from 'react';
import { useQuill } from 'react-quilljs';
import BlotFormatter from 'quill-blot-formatter';
import 'quill/dist/quill.snow.css';

import './styles.css';

const EditorWithUseQuill = () => {
    const { quill, quillRef, Quill } = useQuill({
        modules: { blotFormatter: {} },
    });

    if (Quill && !quill) {
        Quill.register('modules/blotFormatter', BlotFormatter);
    }

    useEffect(() => {
        if (quill) {
            quill.on('text-change', (delta, oldContents) => {
                console.log('Text change!');
                console.log(delta);

                let currrentContents = quill.getContents();
                console.log(currrentContents.diff(oldContents));
            });
        }
    }, [quill, Quill]);

    return (
        <div>
            <div ref={quillRef} />
        </div>
    );
};

export default EditorWithUseQuill;
