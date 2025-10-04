import React, { useState } from 'react';

const Contact: React.FC = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    message: ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // In a real app, this would send the data to a backend
    alert('Thank you for your message! We\'ll get back to you soon.');
    setFormData({ name: '', email: '', subject: '', message: '' });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-pastel-purple to-pastel-pink">
      <div className="container mx-auto px-4 py-16">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-5xl font-bold text-center text-white mb-8">Contact Us</h1>
          
          <div className="grid md:grid-cols-2 gap-8">
            <div className="bg-white bg-opacity-90 rounded-xl p-8 shadow-lg">
              <h2 className="text-3xl font-bold text-gray-800 mb-6">Get in Touch</h2>
              <p className="text-lg text-gray-700 mb-8">
                Have questions about Scinkare? Need help with your skincare routine? 
                We'd love to hear from you! Send us a message and we'll get back to you as soon as possible.
              </p>
              
              <div className="space-y-4">
                <div className="flex items-center">
                  <span className="text-2xl mr-4">📧</span>
                  <div>
                    <h3 className="font-semibold text-gray-800">Email</h3>
                    <p className="text-gray-600">hello@scinkare.com</p>
                  </div>
                </div>
                
                <div className="flex items-center">
                  <span className="text-2xl mr-4">💬</span>
                  <div>
                    <h3 className="font-semibold text-gray-800">Support</h3>
                    <p className="text-gray-600">support@scinkare.com</p>
                  </div>
                </div>
                
                <div className="flex items-center">
                  <span className="text-2xl mr-4">🐛</span>
                  <div>
                    <h3 className="font-semibold text-gray-800">Bug Reports</h3>
                    <p className="text-gray-600">bugs@scinkare.com</p>
                  </div>
                </div>
              </div>
            </div>

            <div className="bg-white bg-opacity-90 rounded-xl p-8 shadow-lg">
              <h2 className="text-3xl font-bold text-gray-800 mb-6">Send us a Message</h2>
              
              <form onSubmit={handleSubmit} className="space-y-6">
                <div>
                  <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-2">
                    Name
                  </label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  />
                </div>
                
                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  />
                </div>
                
                <div>
                  <label htmlFor="subject" className="block text-sm font-medium text-gray-700 mb-2">
                    Subject
                  </label>
                  <input
                    type="text"
                    id="subject"
                    name="subject"
                    value={formData.subject}
                    onChange={handleChange}
                    required
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  />
                </div>
                
                <div>
                  <label htmlFor="message" className="block text-sm font-medium text-gray-700 mb-2">
                    Message
                  </label>
                  <textarea
                    id="message"
                    name="message"
                    value={formData.message}
                    onChange={handleChange}
                    required
                    rows={5}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  />
                </div>
                
                <button
                  type="submit"
                  className="w-full bg-pastel-pink text-white py-3 px-6 rounded-lg font-semibold hover:bg-pink-400 transition-colors"
                >
                  Send Message
                </button>
              </form>
            </div>
          </div>

          <div className="bg-white bg-opacity-90 rounded-xl p-8 shadow-lg mt-8">
            <h2 className="text-3xl font-bold text-gray-800 mb-6 text-center">Frequently Asked Questions</h2>
            
            <div className="grid md:grid-cols-2 gap-6">
              <div>
                <h3 className="text-lg font-semibold text-gray-800 mb-2">Is my data secure?</h3>
                <p className="text-gray-700">
                  Yes! All your data is stored locally in your browser using localStorage. 
                  We don't collect or store any personal information on our servers.
                </p>
              </div>
              
              <div>
                <h3 className="text-lg font-semibold text-gray-800 mb-2">Can I use this on my phone?</h3>
                <p className="text-gray-700">
                  Absolutely! Scinkare is fully responsive and works great on all devices, 
                  from desktop computers to mobile phones.
                </p>
              </div>
              
              <div>
                <h3 className="text-lg font-semibold text-gray-800 mb-2">Is this app free?</h3>
                <p className="text-gray-700">
                  Yes, Scinkare is completely free to use. We believe everyone deserves 
                  access to tools that help them take care of their skin.
                </p>
              </div>
              
              <div>
                <h3 className="text-lg font-semibold text-gray-800 mb-2">How do I export my data?</h3>
                <p className="text-gray-700">
                  You can export your data anytime from the dashboard. This feature 
                  will be available in a future update.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Contact;
