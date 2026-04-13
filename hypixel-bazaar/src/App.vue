<script setup lang="ts">
import { ref } from 'vue'

const searchTerm = ref('')
const loading = ref(false)
const error = ref('')
const product = ref<any>(null)
const flips = ref<any[]>([])
const maxPrice = ref(50000)

async function searchItem() {
  if (!searchTerm.value.trim()) return
  
  loading.value = true
  error.value = ''
  product.value = null

  try {
    const res = await fetch('https://api.hypixel.net/v2/skyblock/bazaar')
    const data = await res.json()
    
    const key = Object.keys(data.products).find(k =>
      k.toLowerCase().includes(searchTerm.value.toLowerCase())
    )

    if (key) {
      product.value = { id: key, ...data.products[key] }
    } else {
      error.value = 'Item not found!'
    }
  } catch {
    error.value = 'Error fetching data.'
  } finally {
    loading.value = false
  }
}

async function loadBestFlips() {
  const res = await fetch('https://api.hypixel.net/v2/skyblock/bazaar')
  const data = await res.json()

  const results = Object.entries(data.products)
    .map(([id, p]: [string, any]) => {
      const buy = p.quick_status.buyPrice
      const sell = p.quick_status.sellPrice
      const margin = buy - sell
      const marginPercent = (margin / sell) * 100
      const sellVolume = p.quick_status.sellVolume
      return { id, buy, sell, margin, marginPercent, sellVolume }
    })
.filter(p => p.sell > 100 && p.buy > 100 && p.sell < maxPrice.value && p.margin > 0 && p.sellVolume > 1000 && p.marginPercent < 1000)
    .sort((a, b) => b.marginPercent - a.marginPercent)
    .slice(0, 3)

  flips.value = results
}

loadBestFlips()
</script>

<template>
  <div class="container mt-5">
    <h1 class="text-center mb-4">🏪 Hypixel Bazaar Tracker</h1>

    <div class="input-group mb-3">
      <input 
        v-model="searchTerm" 
        type="text"
        class="form-control" 
        placeholder="Search item... (e.g. WHEAT)"
        @keyup.enter="searchItem"
      />
      <button class="btn btn-primary" @click="searchItem">Search</button>
    </div>

    <p v-if="loading">Loading...</p>
    <p v-if="error" class="text-danger">{{ error }}</p>

    <div v-if="product" class="card mt-3 p-4">
      <h2>{{ product.id }}</h2>
      <div class="row mt-3">
        <div class="col-6 mb-3">
          <div class="card p-3 text-center">
            <span class="text-muted mb-1">Buy Price</span>
            <strong>{{ product.quick_status.buyPrice.toFixed(1) }} coins</strong>
          </div>
        </div>
        <div class="col-6 mb-3">
          <div class="card p-3 text-center">
            <span class="text-muted mb-1">Sell Price</span>
            <strong>{{ product.quick_status.sellPrice.toFixed(1) }} coins</strong>
          </div>
        </div>
        <div class="col-6 mb-3">
          <div class="card p-3 text-center">
            <span class="text-muted mb-1">Buy Volume</span>
            <strong>{{ product.quick_status.buyVolume.toLocaleString() }}</strong>
          </div>
        </div>
        <div class="col-6 mb-3">
          <div class="card p-3 text-center">
            <span class="text-muted mb-1">Sell Volume</span>
            <strong>{{ product.quick_status.sellVolume.toLocaleString() }}</strong>
          </div>
        </div>
      </div>
    </div>

    <div class="mb-3 mt-4">
      <label class="form-label">Max Price (coins)</label>
      <input 
        v-model.number="maxPrice" 
        type="number" 
        class="form-control" 
        placeholder="Max price..."
      />
    </div>

    <div v-if="flips.length > 0" class="mt-4">
      <h3>🔥 Best Flip Opportunities</h3>
      <div class="row mt-3">
        <div v-for="flip in flips" :key="flip.id" class="col-12 mb-3">
          <div class="card p-3">
            <div class="d-flex justify-content-between align-items-center">
              <h5 class="mb-0">{{ flip.id }}</h5>
              <span class="badge bg-success">+{{ flip.marginPercent.toFixed(1) }}%</span>
            </div>
            <div class="row mt-2">
              <div class="col-3 text-center">
                <span class="text-muted">Buy for</span>
                <div><strong>{{ flip.buy.toFixed(1) }}</strong></div>
              </div>
              <div class="col-3 text-center">
                <span class="text-muted">Sell for</span>
                <div><strong>{{ flip.sell.toFixed(1) }}</strong></div>
              </div>
              <div class="col-3 text-center">
                <span class="text-muted">Margin</span>
                <div><strong>{{ flip.margin.toFixed(1) }}</strong></div>
              </div>
              <div class="col-3 text-center">
                <span class="text-muted">Weekly Sales</span>
                <div><strong>{{ flip.sellVolume.toLocaleString() }}</strong></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
html, body {
  background-color: #0f0f1a !important;
  min-height: 100vh;
}

body {
  color: white;
}

.card {
  background-color: #1a1a2e !important;
  border: 1px solid #2a2a4a !important;
  color: white !important;
}

h1 {
  background: linear-gradient(90deg, #6366f1, #a855f7);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-weight: 800;
}

.form-control {
  background-color: #1a1a2e !important;
  border: 1px solid #6366f1 !important;
  color: white !important;
}

.form-control::placeholder {
  color: #666 !important;
}

.btn-primary {
  background: linear-gradient(90deg, #6366f1, #a855f7) !important;
  border: none !important;
}

.text-muted {
  color: #aaa !important;
  font-size: 13px;
}

strong {
  font-size: 20px;
  color: #a855f7;
}
</style>