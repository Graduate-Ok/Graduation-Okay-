import React, { Component } from 'react';
import ReactQuill, { Quill } from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import ImageResize from 'quill-image-resize';

Quill.register('modules/imageResize', ImageResize);

class EditorComponent extends Component {
    constructor(props) {
        super(props);
    }
    modules = {
        toolbar: [
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            ['bold', 'italic', 'underline', 'strike', 'blockquote'],
            [
                { list: 'ordered' },
                { list: 'bullet' },
                { indent: '-1' },
                { indent: '+1' },
            ],
            ['link', 'image', 'video'],
            [{ align: [] }, { color: [] }, { background: [] }],
            ['clean'],
        ],

        imageResize: {
            parchment: Quill.import('parchment'),
            modules: ['Resize', 'DisplaySize'],
        },
    };

    formats = [
        'header',
        'font',
        'size',
        'bold',
        'italic',
        'underline',
        'strike',
        'blockquote',
        'list',
        'bullet',
        'indent',
        'link',
        'image',
        'align',
        'color',
        'background',
        'video',
    ];

    render() {
        const { value, onChange } = this.props;
        return (
            <div style={{ height: '650px' }}>
                <ReactQuill
                    style={{ height: '600px' }}
                    theme="snow"
                    modules={this.modules}
                    formats={this.formats}
                    value={value || ''}
                    onChange={(content, delta, source, editor) =>
                        onChange(editor.getHTML())
                    }
                />
            </div>
        );
    }
}

export default EditorComponent;
